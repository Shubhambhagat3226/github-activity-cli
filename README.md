# GitHub User Activity CLI

This is a Command Line Interface (CLI) built with Java and maven that fetches and displays the recent public activity of a GitHub user using the GitHub API.

This project is a solution for the [GitHub User Activity challenge](https://roadmap.sh/projects/github-user-activity) from **roadmap.sh**.

## Features

- Fetches recent public GitHub events using GitHub API
- Supports CLI arguments:
  - `--user=<username>`
  - `--type=<eventType>`
  - `--limit=<number>`
- Event filtering and limiting (processor layer)
- Clean layered architecture (core, cli separation)
- Custom exception handling (API, validation, rate limit)
- Unit testing for parser and processor


## How to Run

### 1. Clone the repository
```bash
git clone https://github.com/Shubhambhagat3226/github-activity-cli.git
cd github-activity-cli
```

### 2. Build the Project

Using Maven:
```bash
mvn clean package
```

Using Maven Wrapper (no Maven install needed):
```bash
./mvnw clean package
```

### 3. Run the CLI
Using JAR
```bash
java -jar target/github-activity-cli-3.1.0.jar --user=<username>
```

Using CLI Script (Windows)
```bash
github-activity.bat --user=<username>
```

#### Example:
```bash
# Basic usage
github-activity.bat --user=shubhambhagat3226

# Advanced Filtering
github-activity.bat --user=shubhambhagat3226 --type=PushEvent --limit=5

# Show help 
github-activity.bat --help
```

#### Example output:
```
- 2 days ago → Pushed 3 commits to username/repository
- 5 days ago → Created repository username/repository
- 16 days ago → Starred owner/repository
```


## Architecture

The application follows a layered design:

CLI → Parser → Service → Processor → Formatter

- CLI handles user input and output
- Service communicates with GitHub API
- Parser converts JSON to Java objects
- Processor applies filtering and business logic
- Formatter handles CLI output


## Project Structure
```
src/main/java/com/githubactivity
├── cli/ # CLI entry point and argument parsing
├── core/
│ ├── model/ # Data models
│ ├── service/ # API communication
│ ├── parser/ # JSON parsing
│ ├── processor/ # Filtering and business logic
│ └── exception/ # Custom exceptions
```


## Requirements
- Java 21
- (Optional) Maven — or use Maven Wrapper (mvnw)


## Future Improvements

- Pagination support
- Convert to Spring Boot REST API
- Add caching for API responses
- Add logging framework (SLF4J)
- Support more event types dynamically
