import express from "express";
import songs from "./songsRoutes.js";

const routes = (app) => {
    app.route("/").get((req, res) => res.status(200).send("learning nodejs"));

    app.use(express.json(), songs)
};

export default routes;