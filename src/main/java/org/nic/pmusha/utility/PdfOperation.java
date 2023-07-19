package org.nic.pmusha.utility;

import org.springframework.stereotype.Component;

@Component
public class PdfOperation {
    public boolean isPdf(byte[] data) {
        if (data == null || data.length < 5) return false;
        // %PDF-
        if (data[0] == 0x25 && data[1] == 0x50 && data[2] == 0x44 && data[3] == 0x46 && data[4] == 0x2D) {
            int offset = data.length - 8, count = 0; // check last 8 bytes for %%EOF with optional white-space
            boolean hasSpace = false, hasCr = false, hasLf = false;
            while (offset < data.length) {
                if (count == 0 && data[offset] == 0x25) count++; // %
                if (count == 1 && data[offset] == 0x25) count++; // %
                if (count == 2 && data[offset] == 0x45) count++; // E
                if (count == 3 && data[offset] == 0x4F) count++; // O
                if (count == 4 && data[offset] == 0x46) count++; // F
                // Optional flags for meta info
                if (count == 5 && data[offset] == 0x20) hasSpace = true; // SPACE
                if (count == 5 && data[offset] == 0x0D) hasCr = true; // CR
                if (count == 5 && data[offset] == 0x0A) hasLf = true; // LF / EOL
                offset++;
            }

            if (count == 5) {
                String version = data.length > 13 ? String.format("%s%s%s", (char) data[5], (char) data[6], (char) data[7]) : "?";
                System.out.printf("Version : %s | Space : %b | CR : %b | LF : %b%n", version, hasSpace, hasCr, hasLf);
                return true;
            }
        }

        return false;
    }
}
