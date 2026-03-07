# GitHub User Activity CLI

This is a Command Line Interface (CLI) built with Java that fetches and displays the recent public activity of a GitHub user using the GitHub API.

This project is a solution for the [GitHub User Activity challenge](https://roadmap.sh/projects/github-user-activity) from **roadmap.sh**.

## Features
- Fetches recent public events (Push, Star, Create) for a specific user.
- Displays human-readable time formatting (e.g., 2 days ago).
- Uses the Jackson library for professional JSON parsing.
- Organized project structure with services, models, and formatters.

## Project Structure
```
src/main/java
├── GithubActivity.java        # Main entry point
├── service/                   # API request handling
├── parser/                    # JSON to Java object logic
├── models/                    # Data objects (Event, Repo, Payload)
└── formatter/                 # CLI output formatting
```


## How to Run

### 1. Clone the repository
```bash
git clone https://github.com/Shubhambhagat3226/github-activity-cli.git
cd github-activity-cli
```

### 2. Compile the Java files
Make sure you include the Jackson library in the classpath during compilation:
```bash
javac -cp "lib/*" src/main/java/GithubActivity.java src/main/java/formatter/*.java src/main/java/models/*.java src/main/java/parser/*.java src/main/java/service/*.java
```

### 3. Run the application
```bash
java -cp ".:lib/*:src/main/java" GithubActivity <username>
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
- Integration with Maven for better dependency management.
- More detailed commit information for Push events.