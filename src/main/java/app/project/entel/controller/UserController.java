package app.project.entel.controller;

import app.project.entel.dto.ResponseDTO;
import app.project.entel.dto.UserDTO;
import app.project.entel.entity.UserEntity;
import app.project.entel.request.UserRequest;
import app.project.entel.response.UserResponse;
import app.project.entel.service.UserService;
import app.project.entel.util.ENTELUtil;
import app.project.entel.vo.MessageVO;
import app.project.entel.vo.ResultVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/getUserEntel")
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getInsert", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody(required = true) UserDTO userDTO) {
        ResultVO salida = new ResultVO();
        String[] mensajes = ENTELUtil.Codigos.ADDOOK.split(";");
        String[] timestamp = ENTELUtil.getCurrentTimeStamp().split(";");

        try {
            if (userDTO.getEmail() ==null || userDTO.getEmail().isEmpty()){
                log.error("No se ha podido agregar el usuario");
                mensajes = ENTELUtil.Codigos.ERRORUSER.split(";");
                salida.setPeticion(new MessageVO(timestamp[0],mensajes[1]));
                return new ResponseEntity<ResultVO>(salida, HttpStatus.INTERNAL_SERVER_ERROR);
            }else{
                userService.addUserMapping(userDTO);
                log.info("Usuario agregado exitosamente ! ");
                MessageVO mensaje = new MessageVO(timestamp[0],mensajes[1]);
                salida.setPeticion(mensaje);
                return new ResponseEntity<>(salida, HttpStatus.OK);
            }

        } catch (Exception e) {
            log.error("Proceso finalizado debido a un error. Revisar log para mas detalles.");
            mensajes = ENTELUtil.Codigos.UNEXPECTEDERROR.split(";");
            salida.setPeticion(new MessageVO(timestamp[0],mensajes[1]));
            return new ResponseEntity<ResultVO>(salida, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/getAllUser")
    public ResponseEntity<?> getAllUser(@RequestBody(required = true) UserRequest userRequest){

        ResultVO resp = new ResultVO();

        ObjectMapper mapper = new ObjectMapper();

        UserResponse userResponse = new UserResponse();

        String[] timestamp = ENTELUtil.getCurrentTimeStamp().split(";");

        String[] mensajes = new String[3];

        try{
            if(userRequest == null || userRequest.getUser() == null || userRequest.getUser().isEmpty()){

                mensajes = ENTELUtil.Codigos.INVALIDPARAMETERS.split(";");
                log.error("Los parametros entregados no son correctos. ");
                resp.setPeticion(timestamp[0],mensajes[1]);

                return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
            }
            log.info("[getAllUser] - REQUEST - {}", mapper.writeValueAsString(userRequest));
            userResponse.setUsers(userService.getAllUser(userRequest.getUser()));

            if(userResponse == null) {
                mensajes = ENTELUtil.Codigos.NOTDATAFOUND.split(";");
                log.error("Ha ocurrido un error al obtener la data.");
                resp.setPeticion(timestamp[0], mensajes[1]);
                return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
            }
            mensajes = ENTELUtil.Codigos.INFOOK.split(";");
            log.info("Datos encontrados correctamente. ");
            resp.setObject(userResponse);
            resp.setPeticion(timestamp[0], mensajes[1]);
            log.debug("[getAllUser] - RESPONSE - {}", mapper.writeValueAsString(resp));
            return new ResponseEntity<>(resp, HttpStatus.OK);

        }catch (Exception e){
            mensajes = ENTELUtil.Codigos.UNEXPECTEDERROR.split(";");
            log.error("Ha ocurrido error al obtener la data {}", e.getMessage());
            resp.setPeticion(timestamp[0], mensajes[1]);
            return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getByUser/{email}")
    public ResponseEntity<ResponseDTO> findByUser(@PathVariable("email") String email){

        try {
            List<UserEntity> user = userService.findByUser(email);
            return new ResponseEntity<ResponseDTO>(ResponseDTO
                    .builder()
                    .status(Objects.nonNull(user))
                    .message(Objects.nonNull(user) ? "Usuarios obtenidos con éxito ✔️ " : " Ha ocurrido un ERROR para obtener los usuarios ❌ ")
                    .data(user)
                    .build(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ResponseDTO>(ResponseDTO
                    .builder()
                    .status(false)
                    .message("Error al obtener usuario " + e.getMessage())
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
