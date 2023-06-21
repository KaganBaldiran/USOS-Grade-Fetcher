import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.exit;

public class WebHandler
{
    WebHandler()
    {

    }

    public static void SendMeDailyPosts()
    {
        Thread LoadingThread = new Thread(new LoadingDialog());
        LoadingThread.start();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://cas.usos.tu.kielce.pl/cas/login?service=https%3A%2F%2Fusosweb.tu.kielce.pl%2Fkontroler.php%3F_action%3Dlogowaniecas%2Findex&locale=plt");
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        username.sendKeys(ReadDataFromJSONFile("credentials.json" , "username"));
        password.sendKeys(ReadDataFromJSONFile("credentials.json" , "password"));

        driver.findElement(By.name("submit")).click();

        boolean Allow = false;

        try
        {
            driver.findElement(By.className("error"));
        } catch (Exception e) {
            Allow = true;
        }

        if(Allow) {

            String href = null;
            boolean TechnicalError = false;

            driver.findElement(By.name("DLA STUDENTÓW")).click();
            try {
                href = driver.findElement(By.xpath("//*[@id=\"layout-c22\"]/usos-module-link-tile-container/usos-module-link-tile[3]")).getAttribute("href");

            } catch (RuntimeException e) {
                TechnicalError = true;
            }

            if(!TechnicalError) {

                driver.get(href);

                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

                String currentlink = driver.getCurrentUrl();
                driver.get(currentlink + "&lang=en");

                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));

                List<WebElement> tables = driver.findElements(By.className("expand-collapse"));

                String University_name = driver.findElement(By.tagName("app-header")).getText();

                String user_name = driver.findElement(By.id("layout-cas-bar")).getAttribute("logged-user");

                String semesterfromfile = ReadDataFromJSONFile("credentials.json", "semester");

                String path = "grades.txt";

                boolean Semesterfound = false;

                if (tables.size() != 0) {
                    for (WebElement table : tables) {
                        String semester = table.findElement(By.className("ec-header")).getText();

                        if (semester.equalsIgnoreCase(semesterfromfile)) {

                            Semesterfound = true;
                            String table_content = table.getText();
                            StringBuilder sb = new StringBuilder(table_content);


                            String finalstring = "";

                            for (int i = 0; i < sb.length() - 7; i++) {

                                if (sb.substring(i, i + 7).equalsIgnoreCase("details")) {
                                    System.out.println("EQUALS!");
                                    sb.insert(i + 7, "\n");

                                }
                            }

                            sb.insert(0, University_name + "\n" + "Student: " + user_name + "\nData was fetched using the web automating script written by Kagan Baldiran\n\n");
                            finalstring = sb.toString();

                            try {
                                Files.write(Path.of(path), finalstring.getBytes());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }

                    }

                    driver.quit();
                    LoadingThread.interrupt();

                    if (!Semesterfound) {
                        JOptionPane.showMessageDialog(null, "Incorrect semester name!", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    if (Semesterfound) {
                        try {
                            Desktop.getDesktop().open(Path.of(path).toFile());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }


                } else {
                    driver.quit();
                    LoadingThread.interrupt();
                    JOptionPane.showMessageDialog(null, "No table found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {

                driver.quit();
                LoadingThread.interrupt();
                JOptionPane.showMessageDialog(null,"Technical Break!" , "Error" , JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            driver.quit();
            LoadingThread.interrupt();
            JOptionPane.showMessageDialog(null,"Incorrect credentials!" , "Error" , JOptionPane.ERROR_MESSAGE);
        }

        exit(1);
    }


    public static String ReadDataFromJSONFile(String filename , String DataName)
    {
        try
        {
            String filecontent = new String(Files.readAllBytes(Paths.get(filename)));

            JSONArray jsonArray = new JSONArray(filecontent);

            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.has(DataName))
                {
                    return jsonObject.getString(DataName);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error reading the game meta data! :: key{ " + DataName + " }" );
            e.printStackTrace();
        }
        return filename;
    }

    static class LoadingDialog implements Runnable
    {
        String[] loadingMessages = {
                "Hold tight, we're preparing an extraordinary experience for you.",
                "Loading... Unleashing the power of code.",
                "Embrace the anticipation, greatness is loading.",
                "Preparing to take off into the digital realm.",
                "Just a few more moments, and the magic will begin.",
                "Loading... Discovering new dimensions of possibilities.",
                "Sit back and relax while we create digital wonders for you.",
                "Loading... Fueling the engines of innovation.",
                "Patience, young Padawan. The force of progress is awakening.",
                "Loading... Assembling the building blocks of your dreams.",
                "Time is relative, but our progress is constant.",
                "Loading... Forging a path to technological brilliance.",
                "Shhh... Can you hear the whispers of progress?",
                "Loading... Harnessing the power of imagination.",
                "Rome wasn't built in a day, and neither is groundbreaking software.",
                "Loading... Igniting the sparks of innovation.",
                "Hold on tight, we're about to enter a realm of digital wonders.",
                "Loading... Crafting a symphony of code and creativity.",
                "The gears are turning, progress is on its way.",
                "Loading... Unlocking the gates to a digital universe.",
                "Loading... Infusing your experience with cutting-edge technology.",
                "Hold on, we're paving the way for a seamless journey.",
                "Preparing for greatness. Loading in progress.",
                "Loading... Initiating the launch sequence.",
                "Sit tight, we're optimizing every bit for optimal performance.",
                "Loading... Unleashing the full potential of innovation.",
                "Just a moment, we're assembling the digital pieces.",
                "Loading... Empowering you with next-level features.",
                "Hold your breath, we're diving into the realm of possibilities.",
                "Loading... Creating a world tailored just for you.",
                "Stay patient, we're crafting a masterpiece behind the scenes.",
                "Loading... Unraveling the mysteries of code.",
                "Brace yourself, we're about to embark on a digital adventure.",
                "Loading... Unleashing the power of imagination and technology.",
                "Hang in there, we're weaving together the threads of innovation.",
                "Loading... Elevating your experience to new heights.",
                "Get ready, we're about to unveil a new era of possibilities.",
                "Loading... Blazing the trail towards a brighter future.",
                "Patience, we're aligning the stars for a remarkable experience.",
                "Loading... Harnessing the potential of ones and zeros."
        };

        String CurrentMessage;

        LoadingDialog()
        {
            super();
            CurrentMessage = loadingMessages[ThreadLocalRandom.current().nextInt(0,40)];
        }

        @Override
        public void run() {
            JOptionPane.showOptionDialog(null, CurrentMessage ,"Process" , JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
        }
    }

}
