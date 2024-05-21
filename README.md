# Solución al reto técnico de generación de ordenes de compra

Creé este repositorio para la solución al reto de Talento B. Consta de un frontend desarrollado en Angular y un backend en Spring Boot que genera PDFs.

Demostración:

https://github.com/sant-17/pdf-generator-4-purchase-orders/assets/104281267/d322296f-1ccc-40c8-b97c-4037c88f4488

## 🚀 Acerca de mí
Buen día, 

Soy Santiago García Herrera, estudiante de Ingeniería de Sistemas de octavo semestre y (autoproclamado) Desarrollador Backend con Java/SpringBoot. 


## Historias de Usuario

Creé unas historias de usuario (HU apartir de ahora en los commits del proyecto y en este Read.me) para resolver el reto sin embargo, no he agregado criterios de aceptación para no extender Read.me

- **HU-1. Formulario de Orden de Compra**:
    Como cliente de MECATICO COMPANY,
    Quiero ingresar mi número de cédula o NIT, mi número de teléfono y seleccionar al menos tres productos para ordenar,
    Para poder generar y descargar mi orden de compra en formato PDF.

- **HU-2. Generación y Descarga de PDF**: 
    Como cliente de MECATICO COMPANY,
    Quiero que los datos ingresados en el formulario sean recopilados y transformados en un archivo PDF,
    Para poder tener una copia de mi orden de compra.

- **HU-3. Validaciones del Formulario**:
    Como cliente de MECATICO COMPANY,
    Quiero recibir retroalimentación inmediata si ingreso datos incorrectos en el formulario,
    Para asegurarme de que todos los datos sean válidos antes de generar el PDF.

- **HU-4. Mejora de la Interfaz de Usuario**:
    Como cliente de MECATICO COMPANY,
    Quiero que la interfaz del formulario sea fácil de usar y visualmente atractiva,
    Para mejorar mi experiencia al ingresar una orden de compra.

- **HU-5. Integración del Backend**:
    Como desarrollador del sistema de órdenes de compra,
    Quiero integrar un backend en Java que reciba los datos del formulario,
    Para generar el PDF en el servidor y enviar el archivo al frontend para su descarga.

## Estructura del Proyecto

El proyecto está dividido en dos partes principales:

- `frontend/order-app`: Aplicación frontend desarrollada en Angular.
- `backend/pdf-generator`: Servicio backend desarrollado en Spring Boot.

## Características

- Formulario para la creación de órdenes de compra.
- Lista predefinida de productos.
- Validación de campos en el formulario.
- Generación de un PDF con la orden de compra.

## Tecnologías Utilizadas

- **Frontend**: Angular, Angular Material
- **Backend**: Spring Boot, iText para la generación de PDFs
- **Otros**: Maven, Node.js

## Configuración y Ejecución

### Backend

1. Navega al directorio del backend:

    ```sh
    cd backend/pdf-generator
    ```

2. Construye y ejecuta el proyecto:

    ```sh
    mvn clean install
    mvn spring-boot:run
    ```

3. El servidor backend se ejecutará en `http://localhost:8080`.

### Frontend

1. Navega al directorio del frontend:

    ```sh
    cd frontend/order-app
    ```

2. Instala las dependencias:

    ```sh
    npm install
    ```

3. Ejecuta la aplicación:

    ```sh
    ng serve
    ```

4. La aplicación frontend estará disponible en `http://localhost:4200`.

## Uso

1. Abre la aplicación frontend en tu navegador (`http://localhost:4200`).
2. Completa el formulario con los datos necesarios.
3. Selecciona los productos y sus cantidades.
4. Haz clic en "Generar PDF" para descargar la orden de compra en formato PDF.
