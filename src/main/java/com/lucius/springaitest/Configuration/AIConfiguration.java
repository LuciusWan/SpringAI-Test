package com.lucius.springaitest.Configuration;

import com.lucius.springaitest.Constant.AIConstant;
import com.lucius.springaitest.Tools.CodeTools;
import com.lucius.springaitest.Tools.CourseTools;
import com.lucius.springaitest.VO.Course;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.TokenCountBatchingStrategy;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.redis.RedisVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPooled;

@Component
public class AIConfiguration {
    @Autowired
    private CourseTools courseTools;
    @Autowired
    private ChatMemory chatMemory;
    @Autowired
    private CodeTools codeTools;
    @Autowired
    private JedisPooled jedisPooled;
    @Autowired
    private OpenAiEmbeddingModel openAiEmbeddingModel;

    @Bean
    public ChatClient chat(OllamaChatModel moddle) {
        return ChatClient.builder(moddle)
                .defaultSystem(AIConstant.DEFAULT).defaultAdvisors(
                        new SimpleLoggerAdvisor(),
                        new MessageChatMemoryAdvisor(chatMemory)
                ).build();
    }

    @Bean
    public ChatClient Alibaba(OpenAiChatModel moddle, ChatMemory chatMemory) {
        return ChatClient.builder(moddle)
                .defaultSystem(AIConstant.PROMPT_MAX).defaultAdvisors(
                        new SimpleLoggerAdvisor(),
                        new MessageChatMemoryAdvisor(chatMemory)
                ).build();
    }

    @Bean
    public ChatClient serviceChatClient(
            OpenAiChatModel model,
            ChatMemory chatMemory) {
        return ChatClient.builder(model)
                .defaultSystem(AIConstant.SERVER)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory),
                        new SimpleLoggerAdvisor())
                .defaultTools(courseTools)
                .defaultTools(codeTools)
                .build();
    }

    @Bean
    public ChatClient GirlfriendChatClient(OpenAiChatModel model, ChatMemory chatMemory) {
        return ChatClient.builder(model)
                .defaultSystem(AIConstant.GIRL_FIREND_PROMAX)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory),
                        new SimpleLoggerAdvisor())
                .build();
    }

    @Bean
    public ChatClient GirlfriendSadChatClient(OpenAiChatModel model, ChatMemory chatMemory) {
        return ChatClient.builder(model)
                .defaultSystem(AIConstant.GIRLFRIEND)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory),
                        new SimpleLoggerAdvisor())
                .build();
    }

    @Bean
    public ChatClient ComputerNetWorkChatClient(OpenAiChatModel model, ChatMemory chatMemory) {
        return ChatClient.builder(model)
                .defaultSystem(AIConstant.COMPUTER_NETWORK)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory),
                        new SimpleLoggerAdvisor())
                .build();
    }

    @Bean
    public ChatClient GirlfriendAIChatClient(OpenAiChatModel model, ChatMemory chatMemory) {
        return ChatClient.builder(model)
                .defaultSystem(AIConstant.GIRL_FRIEND_AI)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory),
                        new SimpleLoggerAdvisor())
                .build();
    }

    @Bean
    public ChatClient EnglishHomework(OpenAiChatModel model, ChatMemory chatMemory) {
        return ChatClient.builder(model)
                .defaultSystem(AIConstant.ENGLISH_PAPER)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory),
                        new SimpleLoggerAdvisor())
                .build();
    }

    @Bean
    public ChatClient DefaultChatClient(OpenAiChatModel model, ChatMemory chatMemory) {
        return ChatClient.builder(model)
                .defaultSystem(AIConstant.DEFAULT)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory),
                        new SimpleLoggerAdvisor())
                .build();
    }

    @Bean
    public ChatClient OminiChatClient(OpenAiChatModel model, ChatMemory chatMemory) {
        return ChatClient.builder(model)
                .defaultOptions(ChatOptions.builder().model("qwen-omni-turbo-latest").build())
                .defaultSystem(AIConstant.DEFAULT)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory),
                        new SimpleLoggerAdvisor())
                .build();
    }

    @Bean
    public ChatClient SQLChatClient(OpenAiChatModel model, ChatMemory chatMemory) {
        return ChatClient.builder(model)
                .defaultSystem(AIConstant.SQL_P)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory),
                        new SimpleLoggerAdvisor())
                .build();
    }

    @Bean
    public ChatClient LearningChatClient(OpenAiChatModel model, ChatMemory chatMemory) {
        return ChatClient.builder(model)
                .defaultSystem(AIConstant.LEARNING)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory),
                        new SimpleLoggerAdvisor())
                .build();
    }

    @Bean
    public ChatClient AICoding(OpenAiChatModel model, ChatMemory chatMemory) {
        return ChatClient.builder(model)
                .defaultSystem(AIConstant.AI_CODE)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory),
                        new SimpleLoggerAdvisor())
                .build();
    }

    @Bean
    public ChatClient AICodingNext(OpenAiChatModel model, ChatMemory chatMemory) {
        return ChatClient.builder(model)
                .defaultSystem(AIConstant.AI_CODE_NEXT)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory),
                        new SimpleLoggerAdvisor())
                .build();
    }

    @Bean
    public ChatClient DesignPattern(OpenAiChatModel model, ChatMemory chatMemory) {
        return ChatClient.builder(model)
                .defaultSystem(AIConstant.DESIGN_PATTERN)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory),
                        new SimpleLoggerAdvisor())
                .build();
    }

    @Bean
    public VectorStore simpleVectorStore(OpenAiEmbeddingModel embeddingModel) {
        return SimpleVectorStore.builder(embeddingModel).build();
    }

    @Bean
    public VectorStore redisVectorStore() {
        return RedisVectorStore.builder(jedisPooled, openAiEmbeddingModel)
                .indexName("custom-index")                // 可选：默认 spring-ai-index
                .prefix("custom-prefix")                  // 可选：默认 embedding:
                .metadataFields(                         // 定义元数据字段
                        RedisVectorStore.MetadataField.tag("country"),
                        RedisVectorStore.MetadataField.numeric("year"))
                .initializeSchema(true)                   // 是否初始化索引
                .batchingStrategy(new TokenCountBatchingStrategy()) // 可选：默认 TokenCountBatchingStrategy
                .build();

    }
}
