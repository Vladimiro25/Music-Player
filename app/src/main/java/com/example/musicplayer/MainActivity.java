package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ImageView playPauseIcon;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this,R.raw.stuff);
        playPauseIcon = findViewById(R.id.playImageView);
        seekBar = findViewById(R.id.seekBar);
        //setMax - Устанавливает максимальный верхний диапазон индикатора выполнения.
        //getDuration() - Gets the duration of the file. getDuration(int) - продолжительность в миллисекундах, если продолжительность недоступна (например, при потоковой передаче живого контента), возвращается -1.
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b)
                {
                    mediaPlayer.seekTo(i);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //public void scheduleAtFixedRate (TimerTask task, long delay, long period) - Планирует указанную задачу для повторного выполнения с фиксированной скоростью, начинающегося после указанной задержки. Последующие выполнения происходят примерно через равные промежутки времени, разделенные указанным периодом.
        //
        //При выполнении с фиксированной скоростью каждое выполнение планируется относительно запланированного времени выполнения начального выполнения. Если выполнение задерживается по какой-либо причине (например, сборка мусора или другая фоновая активность), два или более выполнения будут происходить в быстрой последовательности, чтобы "наверстать упущенное". В долгосрочной перспективе частота выполнения будет в точности равна обратной величине заданного периода (при условии, что системные часы, лежащие в основе Object.wait(long), точны).
        //
        //Выполнение с фиксированной частотой подходит для повторяющихся действий, которые чувствительны к абсолютному времени, например, звонок курантов каждый час по часам или выполнение планового обслуживания каждый день в определенное время. Он также подходит для повторяющихся действий, где важно общее время выполнения фиксированного количества операций, например, таймер обратного отсчета, который тикает раз в секунду в течение десяти секунд. Наконец, выполнение с фиксированной скоростью подходит для планирования нескольких повторяющихся задач таймера, которые должны оставаться синхронизированными друг с другом.
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //setProgress - Устанавливает текущий прогресс на указанное значение. Ничего не делает, если индикатор прогресса находится в неопределенном режиме.
                //Этот метод немедленно обновляет визуальную позицию индикатора прогресса. Чтобы анимировать визуальную позицию до заданного значения, используйте setProgress(int, boolean)}.
                //getCurrentPosition() - Получает текущую позицию воспроизведения.
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,1000);
    }

    public void previous(View view) {
        seekBar.setProgress(0);
        mediaPlayer.seekTo(0);
        mediaPlayer.pause();
        playPauseIcon.setImageResource(R.drawable.ic_baseline_play_arrow_24);
    }

    public void next(View view) {
        seekBar.setProgress(mediaPlayer.getDuration());
        mediaPlayer.seekTo(mediaPlayer.getDuration());
        mediaPlayer.pause();
    }

    public void play(View view) {
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.pause();
            //устанавливает иконку ic_baseline_play_arrow_24 в котором у нас иконка паузы
            playPauseIcon.setImageResource(R.drawable.ic_baseline_play_arrow_24);
        }
        else
        {
            mediaPlayer.start();
            playPauseIcon.setImageResource(R.drawable.ic_baseline_pause_24);
        }
    }
}