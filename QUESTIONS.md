# Questions

Here are 2 questions related to the codebase. There's no right or wrong answer - we want to understand your reasoning.

## Question 1: API Specification Approaches

OpenAPI (contract-first) ensures consistency, auto-generation, and better collaboration across teams, but it is less flexible and slower to iterate. Handwritten APIs are more flexible and faster to develop but can lead to inconsistent design and higher maintenance effort. 

I would prefer a hybrid approach: OpenAPI for public/stable APIs (like Warehouse) and handwritten APIs for internal or fast-changing services (like Store/Product), balancing consistency and flexibility.

## Question 2: Testing Strategy

I would prioritize unit tests first to validate core business logic, followed by integration tests to ensure correct DB and transaction behavior. Concurrency tests are critical for this system due to optimistic locking and race conditions, so they would be focused on key flows. Parameterized tests help cover edge cases efficiently. 

Overall, I would follow a testing pyramid approach and ensure stable, non-flaky tests with continuous CI execution.
