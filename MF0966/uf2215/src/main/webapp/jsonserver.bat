REM winget install openjs.nodejs.lts
REM npm install -g json-server@0.17.4
json-server -w -H 127.0.0.1 -p 3001 -s . json\productos.json
