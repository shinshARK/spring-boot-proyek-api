# TODOs
stuff todo and my notes and rambles
\
\
(probably not enough time for assignment deadline but maybe continue anyways after)

- #### Validation
for null handling, database layer is by @Column(nullable = false), \
but for higher level not sure what to do, @NotNull throws an exception but i want to modify the status codes n stuff, \
probably need custom exception handling for that. \
for now im cheating with ifs
- #### Error Reporting
maybe something like:
```json
{
    "status": 400,
    "error": "Bad Request",
    "message": "The provided data is invalid.",
    "errors": [
        {
            "field": "namaProyek",
            "message": "Name is required."
        },
        {
            "field": "tglMulai",
            "message": "Start date cannot be null."
        }
    ]
}
```
and a bunch more tests corresponding that
- #### Security? Token stuff
- #### Exceptions?
- #### Fix Docs (OpenAPI)?
- #### More endpoints more parameters for them?
