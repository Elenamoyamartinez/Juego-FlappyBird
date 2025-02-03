# FlappyBird - Java/LibGDX

Este repositorio contiene una implementación básica del juego **Flappy Bird** usando la librería **LibGDX** en Java. El objetivo del juego es mantener un pájaro en el aire evitando obstáculos, que son tuberías que aparecen en la pantalla. Al tocar la pantalla, el pájaro asciende, y la gravedad lo hace descender.

## Estructura del proyecto

El código fuente principal se encuentra en el archivo `Main.java`, que es donde se implementa la lógica del juego.

- **Main.java**: Clase principal que contiene el ciclo de vida del juego (inicio, renderizado, actualización y finalización).
- **Textures**: Imágenes utilizadas en el juego (fondo, pájaros, tuberías, etc.).
- **Lógica de juego**: Contiene la lógica para el movimiento de las tuberías, la gravedad que afecta al pájaro, la detección de colisiones y el puntaje.

## Instrucciones para jugar

1. Al iniciar el juego, el pájaro está en el centro de la pantalla.
2. Toca la pantalla para hacer que el pájaro suba. El objetivo es evitar que el pájaro se choque con las tuberías.
3. El juego termina cuando el pájaro toca una tubería o cae al suelo.
4. Después de perder, toca la pantalla para reiniciar el juego.

## Lógica de juego

El juego tiene tres posibles estados:

1. **Esperando (0)**: El juego está en espera, esperando que el jugador toque la pantalla para empezar a jugar.
2. **Jugando (1)**: El juego está en curso. El jugador controla el pájaro y debe evitar las tuberías.
3. **Game Over (2)**: El juego ha terminado. Se muestra una pantalla de "Game Over" y se puede reiniciar tocando la pantalla.

### Mecanismo de puntuación

La puntuación aumenta cada vez que el pájaro pasa una tubería sin chocar con ella. La puntuación se muestra en la parte superior de la pantalla.

## Archivos de recursos

El juego utiliza los siguientes archivos de recursos:

- `fondo.png`: Fondo del juego.
- `flappybirdarriba.png`: Imagen del pájaro en su posición ascendente.
- `flappybirdabajo.png`: Imagen del pájaro en su posición descendente.
- `gameover.png`: Imagen que se muestra cuando el juego termina.
- `tuberiaarriba.png`: Imagen de la parte superior de la tubería.
- `tuberiaabajo.png`: Imagen de la parte inferior de la tubería.

## Imágenes del juego
![image](https://github.com/user-attachments/assets/bbc62cc7-a791-4093-a90e-61cf59daaf4f)
![image](https://github.com/user-attachments/assets/1195cd4e-59c4-4593-8fe2-023c1ed7dbff)
![image](https://github.com/user-attachments/assets/4b66b8b5-8236-479a-ae59-b3cba04b6179)


