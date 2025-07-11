# Sources I am getting based on

+ [Large Language Models (LLMs) - Everything You NEED To Know](https://youtu.be/osKyvYJ3PRM?si=IFwyc8vHmKzpoYAh)

+ [[1hr Talk] Intro to Large Language Models](https://youtu.be/zjkBMFhNj_g?si=QY2BRyNSkOtIf0f0)

---

## Large Language Models (LLMs) - Everything You NEED To Know

+ LLMs are a type of __neural network__, and LLMs are trained on massive amounts of data, generally data that can be found online
    + data can have tons of pre-processing before getting used by the LLM itself
    + a neural network is basically a combination of algorithms with the purpose of finding/recognizing patterns in the data it is working on (simulating how human brain works).

+ LLMs' goal is to understand __natural language__, and as said, they learn by reading data

+ the difference between LLMs and traditional programming:
    + on traditional programming, the programmer tells the computer __what it should do__ in certain situation
    + on LLMs, the programmer tells the computer __how to learn what it should do__ in certain situation

+ an example where traditional programming can't do the work: image recognition
    + with traditional programming, there is no way to instruct every single pattern and tiny difference between, say, a handwritten letter
    + LLMs can learn what an object is if they are shown examples of that same object previously, and by similarity, LLMs will recognize if a new "variation" of this object comes up

+ LLMs are strong at:
    + summarization
    + text generation
    + creative writing
    + QnA
    + programming

+ how do LLMs work, three steps:
    + 1 - __tokenization__ - other neural networks are trained to tokenize long texts
        + a token can be a small word or a piece of a word
    + 2 - __embeddings__ - LLMs turn tokens into embedding vectors, a bunch of numbers that represents the tokens
        + the numbers are stored in a vector database, which is a very optimized database to work with vectors/numbers
            + based on their numbers (placement in the database), it is possible to know how close/related a word is to another word (if closer, more related)
                + an example: __book__ and __worm__ will have their embeddings close to each other, as they are related due to the existence of the word __bookworm__
    + 3 - __Transformers__ - it is possible to make matrix representations with those embedding vectors, by extracting infos and placing them in the matrix - this is done by the multi-head attention algorithm
        + this algorithm returns a set of numbers that informs how much each word and their order impacts the sentence
        + that input matrix is transformed to an output one, that is converted to natural language

    + after all processing, the LLMs will try to predict the next word based on the context of the data, adjusting the weights of the model
        + this is done multiple times, to enhance the quality of the response

+ a final 4th step is called __evaluation__, and it is the phase of testing the quality of the LLM after its training
    + a data sample is used for testing by the __perplexity metric__, which is a comparison between two words, giving the LLM a good score if the words are similar, a bad one if they aren't
    + __RLHF__ (Reinforcement Learning Through Human Feedback): users and testers test the model, giving good and bad scores too
    + the LLMs are adjusted as needed, based on the scores

+ __fine tuning__: basically train a "foundation model" (e.g., GPT) a little bit more to handle specific cases
    + e.g., imagine that you need an AI to be good at handling with clients, looking to pay for your services
        + by adding more samples of, say, conversations or text exchanges between a client and an attendant, the LLM gets better at talking/texting to clients
            + __transfer learning__: pre-learned knowledge is re-used for a certain task
            + __fine-tuning__ is a transfer learning approach, where the parameters of the pre-trained model are trained again on new data

+ challenges and limitations right now and for the future:
    + the LLMs are good at a lot of things, but they still contain flaws on math, logic, etc

    + bias is a huge problem, because the data used by the LLMs are provided by humans, which are naturally biased and have flaws
        + so LLMs may have biased info and even harmful info (the LLM could be racist, for example)
        + some companies provide censorship to their LLMs

    + LLMs only have knowledge about what they got trained
        + for example, on the early days after ChatGPT became famous, once, ChatGPT answered me something like this: "as of my last update on December 2021..."
        + this is getting solved though, as GPT, for example, is now able to browse the web and Grok, which is X/Twitter's LLM is able to access live tweets

    + LLMs __hallucinate__ pretty often - they make up or understand things wrong, and confidently so

    + training LLMs is really expensive: it requires a lot of processing power and electricity + the hardware itself is not cheap
        + nvidia invested a lot on hardware for the LLMs' maths, and their stock price went up

    + ethics:
        + training models with copyrighted material - companies said they didn't, but that was false
        + LLMs will be used for malicious acts, such as scam, misinformation with, e.g., fake images (like the Pope Francis white coat that enraged a considerable amount of people)
        + what about __AGI__ (Artificial General Intelligence), which are those AI robots in sci-fi movies? will they dominate the world if they existed?
        + and lastly, massive unemployment will happen due to LLMs - sooner or later, some professions may be swapped for the usage of AIs
            + lawyers? programmers? writers (for news, for example)?

    + improvement ideas:
        + __mixture of experts__: multiple LLMs merged and fine-tuned to specific subjects - when a prompt is received, one of the experts (fine-tuned LLM) is chosen to generate an answer
        + __multimodality__: being able to take input from many different source types

---

## [1hr Talk] Intro to Large Language Models

+ the information about llama-2-70b below was published by Meta itself.

+ llama-2-70b is Meta AI's LLM, and it is basically made of two files: a __parameters__ file and a __C code__ file
    + the parameters file is 140GB, with 70 billions of parameters

+ resources to train this model:
    + 10 TB of internet text data
    + 6 thousands of GPUs, and for 12 days
    + around 2 million USD in compute costs

+ check moment 11:30 for a diagram of the Transformer neural network architecture

+ the network itself has billions and billions of parameters scattered through it, and we know how to adjust/optimize them to make better predictions, but we dont know how they collaborate with each other to do that - but it works

+ __fine tuning__ is a training that comes after pre-training the model. Basically, just swap the LLM's training on internet documents to another dataset that the developer wishes
    + here, we prioritize the __quality__ of the data over __quantity__
    + the last time the model was trained, tons and tons of data were used to create a model that can answer generic questions
    + the goal in this second training is to turn the model into a specialist in a specific area

+ fine tuning is way cheaper - could be done every once in a week, or even day

### recap so far

    stage 1: have a cluster of 6k GPUs and spend around 2 million USD to train around 10 TB of text - in 12 days, here's your model

    stage 2: work on your dataset (hire people to create good data, for example) - this data usually contains instructions on how to operate, and also Q&A stuff - then, train the model with the new data

    after stage 2, an assistant model is obtained, one that should be tested if it contains misbehaviors, if the responses are good enough, etc
    and to correct wrong answers, fill in the correct answer as an example of training data, and in the next fine tuning, the model will become better at answering the question that it made a mistake last time

+ fine tuning is really necessary, as the base model is usually not too useful
    + for example, Meta's llama base models will ask you more questions or something like it, if you ask them some