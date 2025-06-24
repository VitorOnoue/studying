import mongoose from "mongoose";
import { artistSchema } from "./Artist.js";

const songSchema = new mongoose.Schema({
    id: {
        type: mongoose.Schema.Types.ObjectId
    },
    title: {
        type: String,
        required: true
    },
    album: {
        type: String
    },
    length: {
        type: String,
        required: true
    },
    artist: artistSchema
}, {
    versionKey: false
});

const song = mongoose.model("songs", songSchema);

export default song;