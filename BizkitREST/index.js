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
        dice_value: []

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
app.get("/rooms", (req, res) => {
    return (res.send(JSON.stringify({
        code: 200,
        message: "OK",
        rooms: rooms
    })))
})

app.get("/get_room_dice/", (req, res) => {
    if (!req.query.room_id)
    {   
        res.send(JSON.stringify({
            code: 400,
            message: "missing room id"
        }))
    }
    current_room = rooms.findIndex(room => room.id == req.query.room_id);
   
    return (res.send(JSON.stringify({
        code: 200,
        message: "OK",
        dice_value: rooms[current_room].dice_value
    })))
})

/* PUT REQUEST */
app.put("/join", (req, res) => {
    if (!req.query.user_id || !req.query.room_id)
    {
        return res.send(JSON.stringify({
            code: 400,
            message: "Bad request",
            query: req.query,
        }))
    }

    
    room_index = rooms.findIndex(room => room.id == req.query.room_id);
   
    if (!rooms[room_index].user_list.push(user.find(usr => usr.id == req.query.user_id).name))
    {
        return res.send(JSON.stringify({
            code: 400,
            message: "Error on joining game",
        }))
    }
    else
    {
        return (res.send(JSON.stringify({
            code: 200,
            message: "Joined game successfully",
        })))
    }
})

app.put("/update_dice/", (req, res) => {
    if (!req.query.user_id || !req.query.room_id || !req.query.dice_value)
    {
        return res.send(JSON.stringify({
            code: 400,
            message: "Bad request",
            query: req.query,
        }))
    }
    room_index = rooms.findIndex(room => room.id == req.query.room_id)
    rooms[room_index].dice_value = req.query.dice_value;
    return res.send(JSON.stringify({
        code: 200,
        message: "Dice value successfully updated"
    }))
})

