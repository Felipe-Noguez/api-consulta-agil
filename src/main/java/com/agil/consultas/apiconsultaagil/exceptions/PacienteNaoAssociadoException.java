package com.agil.consultas.apiconsultaagil.exceptions;

public class PacienteNaoAssociadoException extends RegraDeNegocioException{

    public PacienteNaoAssociadoException() {
        super("Esta consulta não está associada a este paciente.");
    }
}
