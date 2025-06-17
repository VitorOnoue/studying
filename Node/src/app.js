import express from "express";
import databaseConnection from "./config/dbConnect.js";
import routes from "./routes/index.js";

const connection = await databaseConnection();

connection.on("error", (err) => {
    console.error("connection error: " + err);
});

connection.once("open", () => {
    console.log("database connection created successfully");
});

const app = express();
routes(app);

// app.get("/songs/:id", (req, res) => {
//     const index = songById(req.params.id);
//     res.status(200).json(songs[index]);
// });

// app.post("/songs", (req, res) => {
//     songs.push(req.body);
//     res.status(201).send("song registered");
// });

// app.put("/songs/:id", (req, res) => {
//     const index = songById(req.params.id);
//     songs[index].title = req.body.title;
//     res.status(200).json(songs);
// });

// app.delete("/songs/:id", (req, res) => {
//     const index = songById(req.params.id);
//     songs.splice(index, 1);
//     res.status(200).json(songs);
// });

export default app;