package mp;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 * Created by mmcalvarez on 10/26/2016.
 */
public class Sound {
    public static final AudioClip BALL = Applet.newAudioClip(Sound.class.getResource("ball.wav"));
    public static final AudioClip GAMEOVER = Applet.newAudioClip(Sound.class.getResource("gameover.wav"));
    public static final AudioClip NEXTROUND = Applet.newAudioClip(Sound.class.getResource("nextRound.wav"));
}
