// JavaScript source code
var express = require("express");
var app = express();

let user = []
let current_user_id = 1;

app.listen(3000, () => {
    console.log("Server running on port 3000");
});

app.post("/user", (req, res) => {
    if (!req.query.name)
    {
        return res.send(JSON.stringify({
            code: 400,
            message: "Missing name"
        }))
    }
    user.push({
        name: req.query.name,
        id: current_user_id,
    })
    current_user_id += 1;
    console.log(user);
    return res.send(JSON.stringify({
        code: 200,            
        user: {
            name: req.query.name,
            id: current_user_id,
        },
        message: "User successfully created" 
    }))
})
