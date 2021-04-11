# JavaDoc Extraction

This branch provides the implementation of the LabelExtractor interface
which extracts JavaDoc from Java methods and labels the methods with the documentation.

The extracted JavaDoc is stored in the Json format which is serialized to be used as a label.
```json
{
  "description": "...",
  "tags": [...]
}
```

Each JavaDoc tag is parsed and stored in the `"tags"` array of the Json JavaDoc format.

Tag parsing is described in the section *JavaDoc Tag Extraction*.

## How to use

To use this feature you need to set the `problem` to `method javadoc extraction` in the config.

### Config Example

`javadoc_config.json`

## Source code

### Entry point

The entry point of the JavaDoc extractor is the file `src/main/kotlin/problem/MethodJavaDocExtraction.kt`.

### JavaDoc Model

The model of the JavaDoc Json format is described in `src/main/kotlin/javadoc/JavaDocModel.kt`

### JavaDoc Tag Extraction

Each JavaDoc tag is parsed and stored as json.

Currently, the feature supports two types of tags: `JavaDocIdentifierTag`, `JavaDocSimpleTag`.
Which are described in `src/main/kotlin/javadoc/JavaDocModel.kt`.

The `JavaDocIdentifierTag` can be used for tags like `@param x Param description` and it stores them in the format:

```json
{
  "tagName": "param",
  "identifierToken": "x",
  "data": "Param description"
}
```

The `JavaDocSimpleTag` is used for tags of type `@tagName stringData` and stores them in the format:

```json
{
  "tagName": "tagName",
  "data": "stringData"
}
```

The tag extraction logic is easily extendable and is located in the file `src/main/kotlin/javadoc/JavaDocTagExtractors.kt`.

