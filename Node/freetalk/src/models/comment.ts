import mongoose from 'mongoose';

const commentSchema = new mongoose.Schema({
    userName: {
        type: String,
        require: true
    },
    content: {
        type: String,
        require: true
    },
});

const Comment = mongoose.model('Post', commentSchema);

export default Comment;