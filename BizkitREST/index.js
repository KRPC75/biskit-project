// JavaScript source code
var express = require("express");
var app = express();


/* List of current connected players */
let user = []

/* List of available game room */
let rooms = []


let current_user_id = 1;
let current_room_id = 1;

app.listen(3000, () => {
    console.log("Server running on port 3000");
});

/* POST REQUEST */

app.post("/user", (req, res) => {
    if (!req.query.name)
    {
        return res.send(JSON.stringify({
            code: 400,
            message: "Missing name"
        }))
    }
    new_user = {
        name: req.query.name,
        id: current_user_id,
    }
    user.push(new_user);
    current_user_id += 1;
    return res.send(JSON.stringify({
        code: 200,            
        user:new_user,
        message: "User successfully created" 
    }))
})

app.post("/room", (req, res) => {
    if (!req.query.name || !req.query.user_id)
    {
        return res.send(JSON.stringify({
            code: 400,
            message: "Bad request",
            query: req.query,
        }))
    }
    new_room = {
        name: req.query.name,
        id: current_room_id,
        user_list: [

        ],
        turn_direction: "clockwise",
        dice_values: []
    }
    new_room.user_list.push(user.find(usr => usr.id == req.query.user_id).name);
    rooms.push(new_room);
   
    current_room_id += 1;
    return (res.send(JSON.stringify({
        code: 200,
        message: "Room successfully created",
        data: new_room
    })))
})

/* GET REQUEST */


/* PUT REQUEST */
