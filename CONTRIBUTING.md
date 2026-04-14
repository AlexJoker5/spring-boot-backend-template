# Contributing to Backend Template

Thank you for contributing to this project! This document describes the workflow, code standards, and expectations for contributions.

## Table of Contents

- [How to Contribute](#how-to-contribute)
- [Branching Strategy](#branching-strategy)
- [Pull Request Guidelines](#pull-request-guidelines)
- [Coding Standards](#coding-standards)
- [Testing](#testing)
- [Issue Reporting](#issue-reporting)
- [Contact](#contact)

## How to Contribute

1. Fork the repository.
2. Create a new branch for your work:
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. Make your changes in small, focused commits.
4. Run tests and verify the application builds successfully.
5. Submit a pull request against the main branch.

## Branching Strategy

- `main` — stable production-ready code.
- `feature/*` — new features or improvements.
- `fix/*` — bug fixes.
- `docs/*` — documentation updates.
- `chore/*` — maintenance tasks and refactorings.

## Pull Request Guidelines

- Use a clear title and description.
- Reference the issue or change request if applicable.
- Keep PRs small and focused.
- Include screenshots or logs if the change affects behavior.
- Confirm that all tests pass before requesting review.

## Coding Standards

- Follow Java conventions used in the project.
- Keep method and class names descriptive.
- Add Javadoc comments for public classes and methods.
- Use meaningful variable names.
- Validate DTO requests using Jakarta Validation annotations.
- Keep controller methods thin; move business logic into service classes.
- Prefer `ResponseDto` for API responses.

## Testing

- Run unit and integration tests locally:
  ```bash
  ./mvnw test
  ```
- Ensure new code has adequate test coverage.
- Verify that existing functionality is not broken.

## Issue Reporting

When opening an issue, include:

- A clear description of the problem or requested enhancement.
- Steps to reproduce the issue.
- Expected behavior.
- Actual behavior.
- Any relevant logs or error messages.

## Contact

For questions about this repository, please open a GitHub issue or reach out to the maintainers in the repository.
