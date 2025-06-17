import express from "express";
import SongController from "../controllers/songController.js";

const routes = express.Router();

routes.get("/songs", SongController.getAllSongs);

export default routes;