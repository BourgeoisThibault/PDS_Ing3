import redis

def create_connection():
    try:
        return redis.StrictRedis(host='localhost', port=6379, db=0)
    except():
        print("ERROR DB CONNECTION !")


def set_step_key(step, connection):
    connection.set("step", step)


def get_step_key(connection):
    return connection.get("step")


def set_card_id(card_id, connection):
    connection.set("card_id", card_id)


def set_pin(pin, connection):
    connection.set("pin", pin)


def set_amount(amount, connection):
    connection.set("amount", amount)


def get_card_id(connection):
    return connection.get("card_id")


def get_pin(connection):
    return connection.get("pin")


def get_amount(connection):
    return connection.get("amount")


def initialize_keys(connection):
    reset_all_keys(connection)


def reset_all_keys(connection):
    set_amount("", connection)
    set_pin("", connection)
    set_amount("", connection)