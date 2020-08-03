# geopkg2photos

This small Java application extracts images from GPKG files.

There are a few assumptions about the GPKG files:
* images are stored as binary data in table named 'Photos'
* the field name where images are stored is named 'photos'
* the images are JPGs

This was created as a utility for extracting images from GPKG files exported by SW_Maps Android app.
