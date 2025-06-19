import express from "express";
import SongController from "../controllers/songController.js";

const routes = express.Router();

routes.get("/songs", SongController.getAllSongs);
routes.get("/songs/:id", SongController.getSongById);
routes.post("/songs", SongController.createSong);
routes.put("/songs/:id", SongController.updateSongById);
routes.delete("/songs/:id", SongController.deleteSongById);

export default routes;