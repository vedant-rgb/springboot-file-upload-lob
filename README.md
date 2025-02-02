# Dealing With Files In Spring Boot

## Introduction

When dealing with user-uploaded files in a Spring Boot application, we have two main approaches:

1. **Storing the files in the file system or cloud storage** (e.g., AWS S3, Azure Blob).
2. **Storing the files directly in the database**.

This focuses on storing files in the database using a **BLOB** column, which is handled in JPA via `@Lob`.

## Steps:

1 .Create a enitity to store Documents

We create a simple `Document` entity. The `@Lob` annotation on a `byte[]` field tells JPA that this field should be stored as a **BLOB** (Binary Large Object).

```java
@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;    
    private String fileType;    

    @Lob
    private byte[] fileData;       
}
```

2 .Create a controller to uplaod and view files.

We need to set headers for the same, specifically:

- **Content-Type** (the MIME type)
- **Content-Disposition**:
  - `ContentDisposition.inline().build()` → View document in the browser
  - `ContentDisposition.attachment().build()` → Download document as an attachment


```java
HttpHeaders httpHeaders = new HttpHeaders();
httpHeaders.setContentType(MediaType.parseMediaType(document.getFileType()));
httpHeaders.setContentDisposition(ContentDisposition.inline().build());
```
OR
```java
HttpHeaders httpHeaders = new HttpHeaders();
httpHeaders.setContentType(MediaType.parseMediaType(document.getFileType()));
httpHeaders.setContentDisposition(ContentDisposition.attachment().build());
```










            
