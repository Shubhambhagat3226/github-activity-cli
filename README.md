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
```bash
mvn clean package
```
After building, Maven will generate a runnable JAR inside:
```bash
target/github-activity-cli-3.0.0.jar
```

### 3. Run the CLI
```bash
java -jar target/github-activity-cli-3.0.0.jar --user=<username>
```

#### Example:
```bash
# Basic usage
java -jar target/github-activity-cli-3.0.0.jar --user=shubhambhagat3226

# Advanced Filtering
java -jar target/github-activity-cli-3.0.0.jar --user=torvalds --type=PushEvent --limit=5
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
- Maven 3.9+


## Future Improvements

- Pagination support
- Convert to Spring Boot REST API
- Add caching for API responses
- Add logging framework (SLF4J)
- Support more event types dynamically