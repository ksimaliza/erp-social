-- reiniciar en 100 la secuencia de la tabla de parametros
SELECT setval('erpt_parametro_id_parametro_seq',100);
-- insert parametro tiempo cambiar clave usuario
INSERT INTO erpt_parametro
(id_parametro, nombre_parametro, descripcion_parametro, valor_parametro,fecha_registro, estado) VALUES 
(1, 'Tiempo validez contraseña', 'Tiempo en días de validez contraseña', '30', LOCALTIMESTAMP, '1');