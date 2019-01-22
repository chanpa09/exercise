package main

func main() {
	say("This", "is", "a", "book")
	say("Hi")
}

func say(msg ...string) {
	for _, s := range msg {
		println(s)

	}
}
