import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BatchCreator
{
    public static void Create()
    {
        String batchFileContent = "@echo off\n"
                + "\n"
                + "REM Detect the operating system\n"
                + "if \"%PROCESSOR_ARCHITECTURE%\"==\"AMD64\" (\n"
                + "    set OS_ARCH=64\n"
                + ") else (\n"
                + "    set OS_ARCH=32\n"
                + ")\n"
                + "\n"
                + "REM Install the Java SDK\n"
                + "if \"%OS_ARCH%\"==\"64\" (\n"
                + "    set \"downloadUrl=https://download.oracle.com/java/20/archive/jdk-20.0.1_windows-x64_bin.exe\"\n"
                + "    set \"saveLocation=%~dp0jdk-20.0.1_windows-x64_bin.exe\"\n"
                + ") else (\n"
                + "    set \"downloadUrl=https://download.oracle.com/java/20/archive/jdk-20.0.1_macos-x64_bin.dmg\"\n"
                + "    set \"saveLocation=%~dp0jdk-20.0.1_macos-x64_bin.dmg\"\n"
                + ")\n"
                + "\n"
                + "echo Downloading the SDK...\n"
                + "\n"
                + "REM Download the file using curl or bitsadmin\n"
                + "bitsadmin /transfer DownloadJavaSDK20.0.1 /download /priority high \"%downloadUrl%\" \"%saveLocation%\"\n"
                + "REM Check if the file exists\n"
                + ":check_file\n"
                + "if exist \"%saveLocation%\" (\n"
                + "    echo Download completed.\n"
                + "    echo Installing the SDK...\n"
                + "    start /wait \"\" \"%saveLocation%\"\n"
                + ") else (\n"
                + "    goto check_file\n"
                + ")\n";

        String batchFilePath = "InstallSDK.bat";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(batchFilePath))) {
            writer.write(batchFileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
