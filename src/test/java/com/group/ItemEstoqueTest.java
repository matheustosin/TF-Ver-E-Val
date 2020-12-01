package com.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemEstoqueTest {

  private ItemEstoque itemEstoque;

  @BeforeEach
  void setup() {
    itemEstoque = new ItemEstoque(1, 10);
  }

  @Test
  void testaConstrutorValido() {
    assertNotNull(itemEstoque);
  }

  @Test
  void testaConstrutorInvalido() {
    assertThrows(SistVendasException.class, () -> new ItemEstoque(1, -10));
  }

  @Test
  void testaEntradaValida() {
    itemEstoque.entrada(5);
    assertEquals(15, itemEstoque.getQuantidade());
  }

  @Test
  void testaEntradaInvalida() {
    assertThrows(SistVendasException.class, () -> itemEstoque.entrada(-5));
  }

  @Test
  void testaSaidaValida() {
    itemEstoque.saida(5);
    assertEquals(5, itemEstoque.getQuantidade());
  }

  @Test
  void testaSaidaInsuficiente() {
    assertThrows(SistVendasException.class, () -> itemEstoque.saida(15));
  }

  @Test
  void testaSaidaInvalida() {
    assertThrows(SistVendasException.class, () -> itemEstoque.saida(-5));
  }

  @Test
  void testaDisponibilidade() {
    assertTrue(itemEstoque.disponivel(5));
  }

  @Test
  void testaNaoDisponibilidade() {
    assertFalse(itemEstoque.disponivel(15));
  }
}
