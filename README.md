# Smart Excel <> JSON Parser

## Project Overview
The **Smart Excel <> JSON Parser** is a full-stack application that provides an easy-to-use interface for converting Excel files to JSON and vice versa. It supports multiple sheets encapsulated into a single JSON and ensures proper validation and modularity for a seamless user experience.

## Features
- Convert Excel (.xlsx) files to JSON format
- Convert JSON data back to Excel files
- Support for multiple sheets in a single Excel file
- Validation for proper JSON structure
- User-friendly interface with clear feedback messages
- Backend API for parsing and converting data


## API Endpoints
- `POST /api/convert/excel-to-json` – Converts Excel to JSON
- `POST /api/convert/json-to-excel` – Converts JSON to Excel


## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/smart-excel-json-parser.git
   ```
2. Navigate to the project directory:
   ```bash
   cd smart-excel-json-parser
   ```
3. Build the project:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```
5. Open the UI in a browser:
   ```
   http://localhost:8080
   ```

## API Endpoints
- **POST /api/excel-to-json**: Converts Excel to JSON
- **POST /api/json-to-excel**: Converts JSON to Excel

## Testing
The project includes unit tests written with JUnit and Mockito. To run the tests:
```bash
mvn test
```

## Folder Structure
```
smart-excel-json-parser/
|-- src/
|   |-- main/
|   |   |-- java/
|   |   |   |-- com.smart_excel_json_parser/
|   |   |   |   |-- controller/
|   |   |   |   |-- model/
|   |   |   |   |-- service/
|   |   |-- resources/
|   |-- test/
|   |   |-- java/
|   |   |   |-- com.smart_excel_json_parser/
|   |   |   |-- controller/
|   |   |   |-- service/
```
## Tech Stack
- Java 17
- Spring Boot
- Apache POI
- Maven






