-- Adicionando 4 Pacientes

insert into paciente (nome, telefone)
values ('Mike', '51-98856.0001');

insert into paciente (nome, telefone)
values ('Roger', '51-98856.0002');

insert into paciente (nome, telefone)
values ('James', '51-98856.0003');

insert into paciente (nome, telefone)
values ('Neil', '51-98856.0004');



-- Adicionando consultas com sua relação com o paciente

insert into consulta (data_hora_consulta, especialidade)
values ('2023-08-01 09:00:00', 'Clínico Geral');

insert into paciente_consulta (id_consulta, id_paciente)
values (1, 1);

insert into consulta (data_hora_consulta, especialidade)
values ('2023-08-01 09:15:00', 'Clínico Geral');

insert into paciente_consulta (id_consulta, id_paciente)
values (2, 2);

insert into consulta (data_hora_consulta, especialidade)
values ('2023-08-01 09:30:00', 'Clínico Geral');

insert into paciente_consulta (id_consulta, id_paciente)
values (3, 4);

insert into consulta (data_hora_consulta, especialidade)
values ('2023-08-01 09:45:00', 'Clínico Geral');

insert into paciente_consulta (id_consulta, id_paciente)
values (4, 3);