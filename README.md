# TP2-progra3/2020 (MVC-WindowBuilder)
## Introduccion 
Aplicación que permitirá planificar conexiones telefónicas entre
localidades despobladas, la cual consiste en calcular, luego del ingreso de las
coordenadas de varias localidades, el costo mínimo de una red de conexiones.
Este costo se calculara bajo las siguientes condiciones: relaciones entre costo-km,
porcentaje de aumento entre una conexión si la distancia entre la localidades de
esta conexión supera los 300km y un costo fijo agregado si la conexión si
establece entre localidades de dos provincias distintas.
## Guia de uso
Al iniciar la app ,el usuario deberá ingresar los costos para los cuales se va a
calcular la red mínima de una conexión de localidades y, evidentemente , las
localidades que establecerán las conexiones. Las localidades se ingresaran en un
apartado accedido mediante el botón “Actualizar Localidades” ubicado en el
esquina inferior derecha de la ventana principal. Estas Localidades se identificaran
con 3 valores: el nombre de la localidad, el provincia y sus coordenadas de
ubicación geográficas. Las coordenadas asignadas a estas localidades tendrán un
rango de elección de números en representación de punto flotante que ira de -90 a
90 para latitud y de -180 a 180 para longitud, ya que estas son los rango de
representación cartesiana para estas medidas. Ademas, vale agregar, que así
como es posible que el usuario ingrese una localidad, también este podrá eliminar
alguna de ellas de ser necesario.

Una vez ingresados todos los valores necesarios para realizar el calculo,
este se llamara a través del frame principal mediante el botón “calcular costo
mínimo”;ademas , el frame principal, brindara la opción de visualizar la conexión de
localidades en el mapa mediante el uso del botón “Dibujar conexiones”
