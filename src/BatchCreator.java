import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BatchCreator
{
    public static void Create()
    {
        String batchScript = "@echo off\n" +
                "\n" +
                "REM Detect the operating system\n" +
                "if \"%PROCESSOR_ARCHITECTURE%\"==\"AMD64\" (\n" +
                "    set OS_ARCH=64\n" +
                ") else (\n" +
                "    set OS_ARCH=32\n" +
                ")\n" +
                "\n" +
                "REM Install the Java SDK\n" +
                "if \"%OS_ARCH%\"==\"64\" (\n" +
                "    REM Install the Java SDK for Windows 64-bit\n" +
                "    echo Installing Java SDK 64-bit...\n" +
                "    start /wait \"\" \"exes/jdk-20.0.1_windows-x64_bin.exe\"\n" +
                ") else (\n" +
                "    REM Install the Java SDK for macOS 64-bit\n" +
                "    echo Installing Java SDK 64-bit...\n" +
                "    open -W \"exes/jdk-20.0.1_macos-x64_bin.dmg\"\n" +
                ")\n" +
                "\n" +
                "REM Verify the Java installation\n" +
                "java -version";

        
        String batchFilePath = "InstallSDK.bat";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(batchFilePath))) {
            writer.write(batchScript);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
