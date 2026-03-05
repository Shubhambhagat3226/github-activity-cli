# GitHub User Activity CLI

This is a simple Command Line Interface (CLI) built with Java that fetches and displays the recent activity of a GitHub user using the GitHub API.

This project is a solution for the [GitHub User Activity challenge](https://roadmap.sh/projects/github-user-activity) from **roadmap.sh**.

## Features
- Fetches recent public events (Push, Star, Create) for a specific user.
- Uses Java's native `HttpClient` (no external dependencies).
- Parses API data using Regular Expressions (Regex).

## How to Run

### 1. Clone the repository
```bash
git clone https://github.com/Shubhambhagat3226/github-activity-cli.git
cd github-activity-cli
```

### 2. Compile the Java files
```bash
javac GithubActivity.java GithubService.java
```

### 3. Run the application
```bash
java GithubActivity <username>
```
