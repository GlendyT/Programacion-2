-- Alumno con la maxima cantidad de tareas entregadas
SELECT a.id_alumno, a.nombre, a.apellido, COUNT(et.id_entrega) AS total_entregas
FROM ALUMNO a
JOIN ENTREGA_TAREA et ON a.id_alumno = et.id_alumno
GROUP BY a.id_alumno, a.nombre, a.apellido
ORDER BY total_entregas DESC
FETCH FIRST 1 ROWS ONLY;

-- Alumno con mas clases asignadas
SELECT a.id_alumno, a.nombre, a.apellido, COUNT(i.id_clase) AS total_clases
FROM ALUMNO a
JOIN INSCRIPCION i ON a.id_alumno = i.id_alumno
GROUP BY a.id_alumno, a.nombre, a.apellido
ORDER BY total_clases DESC
FETCH FIRST 1 ROWS ONLY;
