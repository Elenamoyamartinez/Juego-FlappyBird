package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture fondo;
    private Texture gameOver;
    private Texture[] pajaros;
    private int posicion = 0;
    private float pajaroY = 0;
    private float velocidad = 0;
    private Circle circuloPajaro;
    private int puntuacion = 0;
    private int puntuadionTuberia = 0;
    private BitmapFont font;
    private BitmapFont font2;

    private int estadoJuego = 0; // 0 = esperando, 1 = jugando, 2 = game over
    private float gravedad = 2;
    private float gap = 550;
    private float maximo;
    private Random generadorRandom;
    private float velocidadTuberia = 4;
    private int numerosTuberias = 4;
    private float[] tuberiaX = new float[numerosTuberias];
    private float[] tuberiaOffSet = new float[numerosTuberias];
    private float distanciaTuberias;
    private Rectangle[] rectanguloTuberiaArriba;
    private Rectangle[] rectanguloTuberiaAbajo;

    private Texture tuberiaArriba;
    private Texture tuberiaAbajo;

    private float tiempo = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        fondo = new Texture("fondo.png");
        gameOver = new Texture("gameover.png");
        circuloPajaro = new Circle();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(10);

        font2 = new BitmapFont();
        font2.setColor(Color.WHITE);
        font2.getData().setScale(8);

        pajaros = new Texture[2];
        pajaros[0] = new Texture("flappybirdarriba.png");
        pajaros[1] = new Texture("flappybirdabajo.png");

        tuberiaArriba = new Texture("tuberiaarriba.png");
        tuberiaAbajo = new Texture("tuberiaabajo.png");
        maximo = Gdx.graphics.getHeight() / 2 - gap / 2 - 100;
        generadorRandom = new Random();
        distanciaTuberias = Gdx.graphics.getWidth() * 3 / 4;
        rectanguloTuberiaArriba = new Rectangle[numerosTuberias];
        rectanguloTuberiaAbajo = new Rectangle[numerosTuberias];

        startGame();
    }

    public void startGame() {
        pajaroY = Gdx.graphics.getHeight() / 2 - pajaros[0].getHeight() / 2;

        for (int i = 0; i < numerosTuberias; i++) {
            tuberiaOffSet[i] = (generadorRandom.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);
            tuberiaX[i] = Gdx.graphics.getWidth() / 2 - tuberiaArriba.getWidth() / 2 + Gdx.graphics.getWidth() + i * distanciaTuberias;

            rectanguloTuberiaArriba[i] = new Rectangle();
            rectanguloTuberiaAbajo[i] = new Rectangle();
        }
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (estadoJuego == 1) {

            tiempo++;

            if (tuberiaX[puntuadionTuberia] < Gdx.graphics.getWidth() / 2) {
                puntuacion++;

                if (puntuadionTuberia < numerosTuberias - 1) {
                    puntuadionTuberia++;
                } else {
                    puntuadionTuberia = 0;
                }
            }

            if (Gdx.input.justTouched()) {
                velocidad = -30;
            }



            for (int i = 0; i < numerosTuberias; i++) {
                if (tuberiaX[i] < -tuberiaArriba.getWidth()) {
                    tuberiaX[i] += numerosTuberias * distanciaTuberias;
                    tuberiaOffSet[i] = (generadorRandom.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);
                } else {
                    tuberiaX[i] = tuberiaX[i] - velocidadTuberia;
                }

                batch.draw(tuberiaArriba, tuberiaX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tuberiaOffSet[i]);
                batch.draw(tuberiaAbajo, tuberiaX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - tuberiaAbajo.getHeight() + tuberiaOffSet[i]);

                rectanguloTuberiaArriba[i] = new Rectangle(tuberiaX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tuberiaOffSet[i], tuberiaArriba.getWidth(), tuberiaArriba.getHeight());
                rectanguloTuberiaAbajo[i] = new Rectangle(tuberiaX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - tuberiaAbajo.getHeight() + tuberiaOffSet[i], tuberiaAbajo.getWidth(), tuberiaAbajo.getHeight());
            }

            if (pajaroY > 0) {
                velocidad = velocidad + gravedad;
                pajaroY -= velocidad;
            } else {
                estadoJuego = 2;
            }

        } else if (estadoJuego == 0) {
            if (Gdx.input.justTouched()) {
                estadoJuego = 1;
            }

        } else if (estadoJuego == 2) {
            batch.draw(gameOver, Gdx.graphics.getWidth() / 2 - gameOver.getWidth() / 2, Gdx.graphics.getHeight() / 2 - gameOver.getHeight() / 2);

            if (Gdx.input.justTouched()) {
                estadoJuego = 1;
                startGame();
                puntuacion = 0;
                puntuadionTuberia = 0;
                velocidad = 0;
                tiempo = 0;
            }
        }

        if (posicion == 0) {
            posicion = 1;
        } else {
            posicion = 0;
        }

        batch.draw(pajaros[posicion], Gdx.graphics.getWidth() / 2 - pajaros[posicion].getWidth() / 2, pajaroY);

        int minutos = (int) (tiempo / 60);
        int segundos = (int) (tiempo % 60);
        String tiempoReloj = String.format("%02d:%02d", minutos, segundos);

        font.draw(batch, String.valueOf(puntuacion), 100, 200);
        font2.draw(batch, String.valueOf(tiempoReloj), 100, 2300);

        circuloPajaro.set(Gdx.graphics.getWidth() / 2, pajaroY + pajaros[posicion].getHeight() / 2, pajaros[posicion].getWidth() / 2);

        for (int i = 0; i < numerosTuberias; i++) {
            if (Intersector.overlaps(circuloPajaro, rectanguloTuberiaArriba[i]) || Intersector.overlaps(circuloPajaro, rectanguloTuberiaAbajo[i])) {
                estadoJuego = 2;
            }
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        pajaros[0].dispose();
        pajaros[1].dispose();
        tuberiaAbajo.dispose();
        tuberiaArriba.dispose();
        gameOver.dispose();
        fondo.dispose();
    }
}
