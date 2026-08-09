# Cómo compilar y correr proyectos Java desde la terminal

## Requisitos
- Tener Java instalado (`java` y `javac` disponibles en el PATH)

## Comandos

### 1. Compilar
```cmd
javac -d src src\<paquete>\*.java
```

### 2. Ejecutar
```cmd
java -cp src <paquete>.<ClasePrincipal>
```

## Ejemplo — Practica1
```cmd
cd "c:\Users\glend\Downloads\University\Cuarto Semestre\programacion 2\Practica1"
javac -d src src\practica1\*.java
java -cp src practica1.Practica1
```

## Ejemplo — primerp
```cmd
cd "c:\Users\glend\Downloads\University\Cuarto Semestre\programacion 2\primerp"
javac -d src src\primerp\*.java
java -cp src primerp.Primerp
```

## Notas
- `<paquete>` es el nombre de la carpeta dentro de `src\` (ej. `practica1`, `primerp`)
- `<ClasePrincipal>` es la clase que contiene el método `main` (ej. `Practica1`, `Primerp`)
- Si hay dependencias entre clases, `*.java` compila todas a la vez y evita errores de orden
