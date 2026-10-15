-- crea base de datos y usa innova_formacion

CREATE DATABASE IF NOT EXISTS innova_formacion;

USE innova_formacion;

-- lanza las migraciones de la base de datos--

CREATE TABLE alumnos (
    id_alumno INTEGER PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    email VARCHAR(150),
    telefono VARCHAR(25)
);

CREATE TABLE cursos (
    id_curso INTEGER PRIMARY KEY,
    nombre VARCHAR(120) NOT NULL,
    duracion_horas INTEGER NOT NULL,
    modalidad VARCHAR(20) NOT NULL,

    CHECK (duracion_horas > 0),

    CHECK (
        modalidad IN (
            'Presencial',
            'Semipresencial',
            'Online'
        )
    )
);

CREATE TABLE docentes (
    id_docente INTEGER PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    email VARCHAR(150)
);

CREATE TABLE matriculas (
    id_matricula INTEGER PRIMARY KEY,
    id_alumno INTEGER NOT NULL,
    id_curso INTEGER NOT NULL,
    fecha_matricula DATE NOT NULL,
    nota_final DECIMAL(4,2),

    FOREIGN KEY (id_alumno)
        REFERENCES alumnos(id_alumno),

    FOREIGN KEY (id_curso)
        REFERENCES cursos(id_curso),

    CHECK (
        nota_final IS NULL
        OR nota_final BETWEEN 0 AND 10
    ),

    UNIQUE (id_alumno, id_curso)
);

CREATE TABLE docentes_cursos (
    id_docente INTEGER NOT NULL,
    id_curso INTEGER NOT NULL,
    fecha_asignacion DATE,

    PRIMARY KEY (id_docente, id_curso),

    FOREIGN KEY (id_docente)
        REFERENCES docentes(id_docente),

    FOREIGN KEY (id_curso)
        REFERENCES cursos(id_curso)
);


-- lanza los Seeds de la base de datos--

-- insertamos docente
INSERT INTO innova_formacion.docentes values(1, 'Pedro', 'Ximenez', 'pedro@innova.com');
INSERT INTO innova_formacion.docentes values(2, 'Ana', 'Martínez', 'ana.martinez@innova.com');
INSERT INTO innova_formacion.docentes values(3, 'Carlos', 'García', 'carlos.garcia@innova.com');
INSERT INTO innova_formacion.docentes values(4, 'Elena', 'Sánchez', 'elena.sanchez@innova.com');
-- insertamos cursos
INSERT INTO innova_formacion.cursos values(1, 'Java', 210, 'Semipresencial');
INSERT INTO innova_formacion.cursos values(2, 'Python y Data Science', 180, 'Online');
INSERT INTO innova_formacion.cursos values(3, 'Desarrollo Web Frontend', 250, 'Presencial');
INSERT INTO innova_formacion.cursos values(4, 'Bases de Datos SQL', 120, 'Online');
-- insertamos alumnos
INSERT INTO innova_formacion.alumnos values(1, 'Alfredo', 'Perez', 'alfredo.perez@innova.com', '+34666666666');
INSERT INTO innova_formacion.alumnos values(2, 'María', 'López', 'maria.lopez@innova.com', '+34611223344');
INSERT INTO innova_formacion.alumnos values(3, 'Juan', 'Gómez', 'juan.gomez@innova.com', '+34655443322');
INSERT INTO innova_formacion.alumnos values(4, 'Laura', 'Rodríguez', 'laura.rodriguez@innova.com', '+34677889900');
INSERT INTO innova_formacion.alumnos values(5, 'Diego', 'Fernández', 'diego.fernandez@innova.com', '+34600112233');
-- insertamos docentes_cursos
INSERT INTO innova_formacion.docentes_cursos values(1, 1, '2026-07-28');
INSERT INTO innova_formacion.docentes_cursos values(2, 2, '2026-08-01');
INSERT INTO innova_formacion.docentes_cursos values(3, 3, '2026-08-05');
INSERT INTO innova_formacion.docentes_cursos values(4, 4, '2026-08-10');
INSERT INTO innova_formacion.docentes_cursos values(1, 3, '2026-08-12');
-- insertamos matricula
INSERT INTO innova_formacion.matriculas values(1, 1, 1, '2026-07-28', NULL);
INSERT INTO innova_formacion.matriculas values(2, 2, 1, '2026-08-15', 8.50);
INSERT INTO innova_formacion.matriculas values(3, 2, 2, '2026-08-15', 9.25);
INSERT INTO innova_formacion.matriculas values(4, 3, 2, '2026-08-16', 7.00);
INSERT INTO innova_formacion.matriculas values(5, 4, 3, '2026-08-18', NULL);
INSERT INTO innova_formacion.matriculas values(6, 5, 4, '2026-08-20', 6.50);