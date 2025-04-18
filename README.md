# PuppyCodeReview

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Version](https://img.shields.io/badge/version-0.9-green.svg)](https://github.com/lendle1028/PuppyCodeReview_public)
[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://www.oracle.com/java/)

An AI-powered code review system designed to streamline and enhance the code review process in educational settings.

## 🚀 Features

- **AI-Powered Analysis** - Get comprehensive code reviews using AI
- **Multiple Assessment Dimensions** - Correctness, design, functionality, complexity, and code smell detection
- **Batch Processing** - Review multiple student submissions at once
- **Peer Review Support** - Combines AI analysis with human peer review
- **Web Interface** - Easy-to-use interface for instructors and students
- **API Integration** - RESTful endpoints for system integration

## 📋 Requirements

- Java 11+
- Web server supporting Java web applications
- Internet connection (for AI service integration)

## 🔧 Installation

```bash
# Clone the repository
git clone https://github.com/lendle1028/PuppyCodeReview_public.git

# Navigate to the project directory
cd PuppyCodeReview_public

# Run the jar file
java -jar target/PuppyCodeReview-0.9.jar
```

## 🏁 Quick Start

### Creating an Assignment

1. Access the web interface
2. Navigate to the "Create Assignment" section
3. Fill in the required fields:
    - Programming language
    - Assignment objective
    - Reference solution
4. (Optional) Add subject and author information
5. Click "Create"

### Running Reviews

#### Single Review
```
POST /api/studentReply
{
  "authorName": "Student Name",
  "code": "// Student's code here",
  "createdDate": "2025-04-18T12:00:00Z",
  "lastModified": "2025-04-18T12:00:00Z"
}
```

#### Batch Review
1. Export student submissions from your LMS as a ZIP file
2. Upload the ZIP through the web interface
3. Specify file patterns to match (e.g., `(.)*\.py` for Python files)
4. Start the batch review process


## 🔍 How It Works

PuppyCodeReview leverages the qwen-2.5-coder-32b-instruct model via OpenRouterAI to analyze code submissions. The system evaluates:

- **Correctness**: How well the code meets the specified requirements
- **Design**: Code organization and structure
- **Functionality**: Whether the code works as intended
- **Complexity**: Code maintainability and readability
- **Code Smells**: Detection of anti-patterns like code duplication and long methods

## 🔌 Integration

PuppyCodeReview provides RESTful API endpoints for integration with other systems:

- `/api/studentReply` - Submit student code for review
- `/api/requestPeerReviewTask` - Request peer review tasks

Example integration: [CodeReview Roblox Game](https://www.roblox.com/games/81554050300949/CodeReview)

## 📚 Documentation

Full documentation coming soon!

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

## 📞 Contact

- **Email**: lendle.tseng.archive@gmail.com
- **GitHub**: [lendle1028](https://github.com/lendle1028)

## 🙏 Acknowledgements

This research is partially supported by the 113-2410-H-155-023 project, funded by the National Science and Technology Council, Taiwan, R.O.C.
