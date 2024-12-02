package ror.rh.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import ror.rh.modelo.Empleado;

public interface EmpleadoRepositorio extends JpaRepository<Empleado, Integer> {
}
