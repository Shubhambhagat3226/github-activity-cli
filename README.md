# GitHub User Activity CLI

This is a Command Line Interface (CLI) built with Java and maven that fetches and displays the recent public activity of a GitHub user using the GitHub API.

This project is a solution for the [GitHub User Activity challenge](https://roadmap.sh/projects/github-user-activity) from **roadmap.sh**.

## Features
- Fetches recent public events (Push, Star, Create) for a specific user.
- Displays human-readable time formatting (e.g., 2 days ago).
- Uses the Jackson library for professional JSON parsing.
- Organized project structure with services, models, and formatters.

## Project Structure
```
src/main/java/com/githubactivity
├── GithubActivity.java        # Main entry point
├── service/                   # API request handling
├── parser/                    # JSON to Java object logic
├── models/                    # Data objects (Event, Repo, Payload)
└── formatter/                 # CLI output formatting
```
## Requirements
- Java 21
- Maven 3.9+

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
target/github-activity-cli-2.0-SNAPSHOT.jar
```

### 3. Run the CLI
```bash
java -jar target/github-activity-cli-2.0-SNAPSHOT.jar <github-username>
```

#### Example:
```bash
java -jar target/github-activity-cli-2.0-SNAPSHOT.jar torvalds
```

#### Example output:
```
- 2 days ago → Pushed 3 commits to username/repository
- 5 days ago → Created repository username/repository
- 16 days ago → Starred owner/repository
```

## Future Improvements
- Pagination support for older events.
- Event filtering (e.g., show only Push events).
- More detailed commit information for Push events.