# USOS-Grade-Fetcher

USOS-Grade-Fetcher is a small Java application that automates the process of checking your university grades from the USOS (University Studies Operating System) with just a click.

## Description

This application utilizes web automation techniques to log into the USOS system, navigate to the grade section, and fetch your grades. It simplifies the process and saves you time and effort.

## Features

- Automated Login: The application automatically logs into the USOS system using the credentials provided.
- Grade Fetching: It navigates to the grade section and fetches your current grades.
- Easy Configuration: Initially, you only need to enter your credentials in the JSON file.
- Simplified Access: Running the JAR file will suffice to fetch your grades.

## Prerequisites

- Java Development Kit (JDK) installed on your machine.

## Installation

1. Clone the repository to your local machine.
2. Open the `credentials.json` file and enter your USOS login credentials.
3. Build the project using the following command:
javac -d bin src/*.java
4. Create a JAR file from the compiled classes:
jar cvfm USOS-Grade-Fetcher.jar manifest.txt -C bin .

## Usage

1. Run the JAR file using the following command:
java -jar USOS-Grade-Fetcher.jar

2. The application will automatically log into the USOS system using the credentials provided in the `config.json` file.
3. After successful login, it will navigate to the grade section and fetch your grades.
4. The fetched grades will be displayed in the console output.

## Contributing

Contributions to the USOS-Grade-Fetcher project are welcome! If you have any bug fixes, feature suggestions, or improvements, please follow these steps:

1. Fork the repository and clone it to your local machine.
2. Create a new branch for your feature: `git checkout -b feature/your-feature-name`.
3. Make your changes and test thoroughly.
4. Commit your changes: `git commit -m 'Add some feature'`.
5. Push to the branch: `git push origin feature/your-feature-name`.
6. Open a pull request on GitHub.

## License

This project is licensed under the MIT License.

## Contact

If you have any questions or feedback, feel free to reach out:

- Email: [k.baldiran@hotmail.com](mailto:k.baldiran@hotmail.com)
