package com.githubactivity.cli.args;

public class CliArgumentsParser {
    public CliArguments parse(String[] args) {
        CliArguments cliArgs = new CliArguments();

        for (String arg: args) {

            if (arg.startsWith("--user=")) {
                cliArgs.setUsername(arg.split("=")[1]);
            } else if (arg.startsWith("--type=")) {
                cliArgs.setType(arg.split("=")[1]);
            } else if (arg.startsWith("--limit=")) {
                try {
                    cliArgs.setLimit(Integer.parseInt(arg.split("=")[1]));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid limit value. Must be a number.");
                }
            }
        }

        if (cliArgs.getUsername() == null) {
            throw new IllegalArgumentException("Username is required (--user=)");
        }

        return cliArgs;
    }
}
