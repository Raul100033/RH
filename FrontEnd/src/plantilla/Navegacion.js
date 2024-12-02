import React from 'react';
import { Link } from 'react-router-dom';

export default function Navegacion() {
  return (
    <div classNameName='container'>
        <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
  <div className="container-fluid">
    <a className="navbar-brand" href="/">Sistema de Recursos Humanos</a>
    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span className="navbar-toggler-icon"></span>
    </button>
    <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
      <div className="navbar-nav">
        <a className="nav-link active" aria-current="page" href="/">Inicio</a>
        <Link className="nav-link" to="/agregar">Agregar Empleado</Link>
      </div>
    </div>
  </div>
</nav>
    </div>
  )
}
