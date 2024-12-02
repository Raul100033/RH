package ror.rh.controlador;

import ch.qos.logback.classic.spi.IThrowableProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ror.rh.excepcion.RecursoNoEncontrado;
import ror.rh.modelo.Empleado;
import ror.rh.servicio.IEmpleadoServicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
// http://localhost:8080/rh-app
@RequestMapping("rh-app")
@CrossOrigin(value = "http://localhost:3000")
public class EmpleadoControlador {
    private static final Logger logger = LoggerFactory.getLogger(EmpleadoControlador.class);

    @Autowired
    private IEmpleadoServicio empleadoServicio;

    // http://localhost:8080/rh-app/empleados
    @GetMapping("/empleados")
    public List<Empleado> obtenerEmpleados(){
        List<Empleado> empleados = this.empleadoServicio.listarEmpleados();
        empleados.forEach((empleado -> logger.info(empleado.toString())));
        return empleados;
    }

    @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleado){
        logger.info("Empleado a agregar: " + empleado);
        return this.empleadoServicio.guardarEmpleado(empleado);
    }

    @GetMapping("/empleados/{idEmpleado}")
    public ResponseEntity<Empleado> buscarEmpleadoPorId(@PathVariable int idEmpleado){
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(idEmpleado);
        if (empleado != null)
            return ResponseEntity.ok(empleado);
        else
            throw new RecursoNoEncontrado("No se encontro el empleado con el id: " + idEmpleado);
    }

    @PutMapping("/empleados/{idEmpleado}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Integer idEmpleado, @RequestBody Empleado empleadoRecibido){
        Empleado empleado = this.empleadoServicio.buscarEmpleadoPorId(idEmpleado);
        if (empleado == null)
            throw new RecursoNoEncontrado("Empleado no encontrado con ID: " + idEmpleado);
        empleado.setNombre(empleadoRecibido.getNombre());
        empleado.setDepartamento(empleadoRecibido.getDepartamento());
        empleado.setSueldo(empleadoRecibido.getSueldo());
        empleadoServicio.guardarEmpleado(empleado);
        return ResponseEntity.ok(empleado);
    }

    @DeleteMapping("/empleados/{idEmpleado}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable Integer idEmpleado){
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(idEmpleado);
        if (empleado == null)
            throw new RecursoNoEncontrado("Empleado no encontrado con ID: " + idEmpleado);
        this.empleadoServicio.eliminarEmpleado(empleado);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}