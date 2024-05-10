const express = require("express");
const db = require("./routes/db-config");
const cookieParser = require("cookie-parser");
const app = express();
const cookie = cookieParser();
const PORT = process.env.PORT || 5001;

app.use("/scripts", express.static(__dirname + "/public/scripts"));
app.use("/css", express.static(__dirname + "/public/css"));
app.use("/pages", express.static(__dirname + "/webpages"));

app.set("view engine", "ejs");
app.set("views", "./views");

app.use(cookie);
app.use(express.json());

db.connect((err) => {
    if (err) throw err;
    console.log("DATABASE CONNECTED");
});

app.use("/", require("./routes/pages"));    
app.use("/api", require("./controllers/auth"));
app.listen(PORT);


