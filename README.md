# sbt test cache issue reproducer

Reproducer for an sbt (2.0.3) test-caching bug: changes to test **resources**
(files under `src/test/resources`) do not invalidate sbt's test cache, so
`test` (and even `testFull`) keeps reporting a stale result instead of
re-running the test.

## Setup

Start an sbt shell in the project root:

```
sbt
```

## Reproducing the bug

1. Run the test suite. It should pass, since `hello.txt` contains `hello`:

   ```
   test
   ```

2. Without leaving the sbt shell, edit `src/test/resources/hello.txt` and
   change its content to something else, e.g. `goodbye`. This should
   invalidate `ReproducerSpec`, since it asserts the resource content equals
   `"hello"`.

3. Relaunch the test:

   ```
   test
   ```

   Expected: the test fails, because `hello.txt` no longer contains `hello`.
   Actual: `test` still reports success, using the cached result from step 1.

4. Run `test` again a few more times. The stale (passing) result keeps being
   reported; sbt never notices that the resource file changed, no matter how
   many times `test` is re-run.

5. Try `testFull`, which is documented as "Executes all tests" (i.e. meant to
   bypass the incremental/cached `test` task):

   ```
   testFull
   ```

   This exhibits the same issue: the stale, cached result is reported instead
   of a fresh run that would fail.
