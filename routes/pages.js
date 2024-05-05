const express = require("express");
const loggedin = require("../controllers/loggedin")
const router = express.Router();

router.get("/", loggedin, (req, res) => {
    if(req.user) {
        res.render("index", {status:"loggedin", user:req.user});
    } else {
    res.render("index", {status:"no", user:"nothing"});
    }
})

router.get("/register", (req, res) => {
    res.sendFile("register.html", {root: "./public"});
})

router.get("/login", (req, res) => {
    res.sendFile("login.html", {root: "./public"});
})

module.exports = router;