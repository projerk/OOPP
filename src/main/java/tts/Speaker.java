package tts;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Speaker {

    private static Speaker instance;

    private final VoiceManager voiceManager;
    private final Voice voice;

    private static final String VOICE_NAME = "kevin16";

    private Speaker() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice(VOICE_NAME);

        if (voice != null) {
            voice.allocate(); 
        } else {
            System.out.println("Voice not found: " + VOICE_NAME);
        }
    }

    public static Speaker getInstance() {
        if (instance == null) {
            instance = new Speaker();
        }
        return instance;
    }

    public void speak(String word) {
        if (voice != null || word.equals("")) {
            voice.speak(word);
        }
    }

    public void cleanup() {
        if (voice != null) {
            voice.deallocate();
        }
    }
}