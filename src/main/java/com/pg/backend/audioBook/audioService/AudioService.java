package com.pg.backend.audioBook.audioService;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AudioService {



    public void readData(MultipartFile multipartFile) throws IOException {

        String text;

        try (final PDDocument document = PDDocument.load(multipartFile.getInputStream())) {
            final PDFTextStripper pdfStripper = new PDFTextStripper();
            text = pdfStripper.getText(document);
        } catch (final Exception ex) {

            text = "Error parsing PDF";
        }
        byte[] stringTextData = text.getBytes();
        speakText(text);

    }
    private void speakText(String text) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

        Voice voice;
        VoiceManager vm = VoiceManager.getInstance();

        voice = vm.getVoice("kevin16");

        if (voice != null) {
            voice.setRate(120);
            voice.allocate();
            voice.speak(text);
            voice.deallocate();
        } else {
            throw new RuntimeException("No voice found!");
        }
    }

}
